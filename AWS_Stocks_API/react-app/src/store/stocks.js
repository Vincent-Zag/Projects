const POST_STOCK = "STOCKS/POST_STOCK";
const EDIT_STOCK= "STOCKS/EDIT_STOCK";
const GET_STOCK = "STOCKS/GET_STOCK";
const DELETE_STOCK = "STOCKS/DELETE_STOCK"

const postStock = (stock) => ({
  type: POST_STOCK,
  payload: stock,
});

const editStock = (stock) => ({
  type: EDIT_STOCK,
  payload: stock
});

const getStocks = (stocks) => ({
  type: GET_STOCK,
  payload: stocks,
});

const deleteStock = (id) => ({
  type: DELETE_STOCK,
  payload: id
});

export const fetchAllStock = () => async (dispatch) => {
  const response = await fetch("/api/stocks");
    if (response.ok) {
        const stock = await response.json();
        dispatch(getStocks(stock));
        return response;
    }
};

export const fetchPostPurchase = (stock) => async (dispatch) => {
  const response = await fetch("/api/stocks", {
    method: "POST",
    headers: {
      "Content-Type": "application/json",
    },
    body: JSON.stringify(stock),
  });
  if (response.ok) {
    const stock = await response.json();
    dispatch(postStock(stock));
    return response;
  }
};

export const fetchEditStock = (id, payload) => async (dispatch) => {
  console.log(id)
  const res = await fetch(`/api/purchases/${id}`, {
    method: "PUT",
    headers: {
      "Content-Type": "application/json"
    },
    body: JSON.stringify(payload)
  });
  if (res.ok) {
    const data = await res.json()
    dispatch(editStock(data))
    return data
  }
}

export const fetchDeleteStock = (id) => async (dispatch) => {
  const response = await fetch(`/api/purchases/${id}`, {
    method: "DELETE",
  });
  console.log(response)
  if (response.ok) {
    dispatch(deleteStock(id))
    return response
  }
}

const initialState = {};

export default function reducer(state = initialState, action) {
  let newState;
  switch (action.type) {
    case GET_STOCK:
      newState = action.payload;
      return newState;
    case POST_STOCK:
      newState = Object.assign({}, state);
      newState[action.payload.id] = action.payload;
      return newState;
    case EDIT_STOCK:
      newState = Object.assign({}, state);
      newState[action.payload.id] = action.payload;
      return newState;
    case DELETE_STOCK:
      newState = Object.assign({}, state);
      delete newState[action.payload.id];
      return newState;
    default:
      return state;
  }
}
