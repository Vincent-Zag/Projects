let cartItems = [];

function login() {
    const enteredUsername = document.getElementById('loginUsername').value;
    const enteredPassword = document.getElementById('loginPassword').value;
    const userDataJSON = localStorage.getItem('userData');

    if (!userDataJSON) {
        alert('No registration data found. Please register first.');
        return;
    }

    const userData = JSON.parse(userDataJSON);
    if (enteredUsername === userData.username && enteredPassword === userData.password) {
        alert('Login successful! Redirecting to the Store Page');
        window.location.href = 'products.html';
    } else {
        alert('Login failed. Please check your username and password.');
    }

    document.getElementById('loginUsername').value = '';
    document.getElementById('loginPassword').value = '';
}

function redirectToRegistration() {
    window.location.href = 'registration.html';
}

function isLoggedIn() {
    const userDataJSON = localStorage.getItem('userData');
    return userDataJSON !== null;
}

function initIndexPage() {
    const productsLink = document.querySelector('a[href="products.html"]');
    const pastOrdersLink = document.querySelector('a[href="past_orders.html"]');

    if (isLoggedIn()) {
        productsLink.classList.remove('disabled');
        pastOrdersLink.classList.remove('disabled');
    } else {
        productsLink.classList.add('disabled');
        pastOrdersLink.classList.add('disabled');
    }
}

function logout() {
    localStorage.clear();
    window.location.href = 'login.html';
}

function register() {
    const username = document.getElementById('regUsername').value;
    const password = document.getElementById('regPassword').value;
    const userData = { username, password };
    localStorage.setItem('userData', JSON.stringify(userData));
    document.getElementById('regUsername').value = '';
    document.getElementById('regPassword').value = '';

    goToIndex();
}

function displayUserInfo() {
    const userInfoDiv = document.getElementById('user-info');
    const usernameDisplay = document.getElementById('username-display');
    const logoutLink = document.getElementById('logout-link');
    const loginLink = document.getElementById('login-link');

    if (isLoggedIn()) {
        const userData = JSON.parse(localStorage.getItem('userData'));
        const username = userData.username;
        usernameDisplay.textContent = `Welcome, ${username}!`;
        logoutLink.style.display = 'inline-block';
        loginLink.style.display = 'none'; 
        userInfoDiv.style.display = 'block';
    } else {
        userInfoDiv.style.display = 'none';
        logoutLink.style.display = 'none';
        loginLink.style.display = 'inline-block'; 
    }
}

function initializePage() {
    displayUserInfo();
}

function goToIndex() {
    window.location.href = 'index.html';
}

function placeOrder() {
    if (cartItems.length === 0) {
        alert('Your cart is empty. Please add items to your cart before placing the order.');
        return;
    }

    let orderTotal = cartItems.reduce((total, item) => total + item.price, 0);
    
    const order = {
        items: cartItems,
        total: orderTotal,
        date: new Date().toLocaleDateString(),
    };

    displayOrderSummary(order);
    saveOrderAsPastOrder(order);
    clearCart();
    displayCart();
}

function addToCart(productName, productPrice) {
    const product = {
        name: productName,
        price: productPrice
    };
    cartItems.push(product);
    document.getElementById('cart-count').textContent = cartItems.length;
    saveCartToLocalStorage();

}

function displayCart() {
    const cartList = document.getElementById('cartItems');
    const cartTotal = document.getElementById('cartTotal');
    cartList.innerHTML = '';
    let total = 0;

    cartItems.forEach(item => {
        const listItem = document.createElement('li');
        listItem.textContent = `${item.name} - $${item.price.toFixed(2)}`;
        cartList.appendChild(listItem);
        total += item.price;
    });

    cartTotal.textContent = total.toFixed(2);
}


function checkout() {
    loadCartFromLocalStorage();

    if (cartItems.length === 0) {
        alert('Your cart is empty. Please add items to your cart before checking out.');
        return;
    }

    window.location.href = 'checkout.html';
}

function clearCart() {
    localStorage.removeItem('cart');
    cartItems = [];
}

function saveOrderAsPastOrder(order) {
    const pastOrders = JSON.parse(localStorage.getItem('pastOrders')) || [];
    pastOrders.push(order);
    localStorage.setItem('pastOrders', JSON.stringify(pastOrders));
}

function initializePage() {
    loadCartFromLocalStorage(); 
    displayCart(); 
}

function saveCartToLocalStorage() {
    localStorage.setItem('cart', JSON.stringify(cartItems));
}

function loadCartFromLocalStorage() {
    const cartData = localStorage.getItem('cart');
    cartItems = cartData ? JSON.parse(cartData) : [];
}

initializePage();
initIndexPage();