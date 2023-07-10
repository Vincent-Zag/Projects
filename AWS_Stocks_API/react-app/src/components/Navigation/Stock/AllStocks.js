import React, { useEffect, useState } from "react";
import { useSelector, useDispatch } from "react-redux";
import { NavLink, useHistory } from "react-router-dom";
import * as stockActions from "../../../store/stocks";
import './stock.css'


const Stocks = () => {
    // const user = useSelector((state) => state.session.user);
    const history = useHistory();
    const stocks = Object.values(useSelector((state) => state.stock));
    const filterStocks = stocks.slice(0, 30)

    const dispatch = useDispatch();


    useEffect(() => {
        dispatch(stockActions.fetchAllStock());
    }, [dispatch]);

    const isUp = (stock) => {
        if(stock.percent_change.startsWith('-')) return false
        else return true
    }
    return (
        <div>
            <div className="stock-feed">{filterStocks?.map((stock) => (
                <div key={stock.id}>
                    <div className="item-header3">
                        <div className="left">
                            <img
                            alt='up or down'
                            src={isUp(stock) ? 'https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcRTYV_A3DnhkxO-w2lbQXYqBrEgY7NWN3_f8Q&usqp=CAU' : 'https://d29fhpw069ctt2.cloudfront.net/clipart/93479/preview/arrowdownred_preview_0a62.png'}
                            className="up-or-down"
                            />
                        </div>
                        <div className="right">
                            <div className="caption-wrapper">
                                <NavLink className="caption" to={`/stocks/${stock.id}`} exact={true}>{stock.name} {stock.symbol}</NavLink>
                            </div>
                        </div>
                    </div>
                </div>))}</div>
        </div>
    );
};

export default Stocks;
