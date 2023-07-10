import { React, useEffect, useState, useRef } from 'react';
import { useSelector, useDispatch } from 'react-redux';
import { NavLink, Redirect, useHistory, useLocation } from 'react-router-dom';
import * as stockActions from "../../store/stocks";
// import UploadStock from '../Stock/AddStock'
// import './NavBar.css'
// import Stocks from '../stock/AllStocks';

const NavBar = () => {

    const history = useHistory()
    const dispatch = useDispatch();
    const [showMenu, setShowMenu] = useState(false)
    const [usersNav, setUsersNav] = useState([]);
    const [query, setQuery] = useState("")
    const stocks = Object.values(useSelector((state) => state.stock));
    const ulRef = useRef();

    useEffect(() => {
        dispatch(stockActions.fetchAllStock());
    }, [dispatch]);

    const searchItem = document.querySelector(".user-header-navi")
    const searchParam = Number(useLocation().pathname.split("/")[2]);

    const searchClick = () => {
        setQuery("")
        history.push(`/stocks/${searchItem?.href.slice(-1)}`)
    }

    const searchClick2 = (stockId) => {
        history.push(`/stocks/${stockId}`)
        setQuery("")
    }

    return (
        <nav>
            <div className="navigation-items">
                <div className="Stock-logo">
                    <NavLink className="logo-container" to="/" exact={true}>
                        <i id='home-logo' class="fa-brands fa-tiktok"></i>
                        <div className="logo-text">Stocks</div>
                    </NavLink>
                </div>
                <form className='search-items'>
                    <div className='search-results'>
                        {query && <h5 className='search-results-header'>stocks</h5>}
                        {query ? stocks.filter(stock => {
                            if (query === '') {
                                return stock
                            } else if (stock.name.toLowerCase().includes(query.toLocaleLowerCase())) {
                                return stock
                            }
                        }).map((stock, index) => (
                            <div className='user-suggested-nav' key={stock.id}>
                                <div className='suggested-text'>
                                    <div className='user-header-navi' onClick={() => searchClick2(stock.id)}>{stock.name} ---- {stock.symbol}</div>
                                </div>
                            </div>
                        )) : null}
                    </div>
                    <input className='search-bar' placeholder='Search stocks' type='search' onChange={event => setQuery(event.target.value)}>
                    </input>
                    <hr className="search-divider" />
                    <i id={!searchItem || !query || searchItem === 0 ? "search-icon" : "search-icon-active"} class="fa-solid fa-magnifying-glass"></i>
                    <button className='search-button' disabled={!searchItem?.href || !query || searchItem === 0} onClick={() => searchClick()}>
                    </button>
                </form>
            </div>
        </nav>
    );
}

export default NavBar;
