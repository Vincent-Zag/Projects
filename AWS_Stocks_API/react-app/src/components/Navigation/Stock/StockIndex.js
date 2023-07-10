import React, { useEffect, useState } from "react";
import { useSelector, useDispatch } from "react-redux";
import { NavLink, useHistory, useLocation } from "react-router-dom";
import * as stockActions from "../../../store/stocks";
import './stock.css'




const Stocks = () => {

    const stockId = Number(useLocation().pathname.split("/")[2]);
    const stocks = Object.values(useSelector((state) => state.stock));
    const stock = stocks.filter(stock => stockId === stock.id)[0]
    const dispatch = useDispatch();
    const history = useHistory()

        const isUp = (stock) => {
        if(stock?.percent_change.startsWith('-')) return false
        else return true
    }

    return (
        <div className="indexContainer">
            <div className="left">
                            <img
                            alt='up or down'
                            src={isUp(stock) ? 'https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcRTYV_A3DnhkxO-w2lbQXYqBrEgY7NWN3_f8Q&usqp=CAU' : 'https://d29fhpw069ctt2.cloudfront.net/clipart/93479/preview/arrowdownred_preview_0a62.png'}
                            className="up-or-down"
                            />
            </div>
            <div className="right">
                <div>
                    Name: {stock?.name}
                </div>
                <div>
                    Symbol: {stock?.symbol}
                </div>
                <div>
                    Percent Change: {stock?.percent_change}
                </div>
                <div>
                    Market Cap: {stock?.market_cap}
                </div>
                <div>
                    Revenue: {stock?.revenue}
                </div>
                <div>
                    Price: {stock?.price}
                </div>
                <div>
                    Timestamp: {stock?.timestamp}
                </div>
            </div>
        </div>
    )
};

export default Stocks;
