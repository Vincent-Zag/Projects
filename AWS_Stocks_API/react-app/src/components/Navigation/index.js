import React from 'react';
import { NavLink } from 'react-router-dom';
import './Navigation.css';

function Navigation({ isLoaded }){
	return (
		<ul>
			<li>
				<NavLink exact to="/">Home</NavLink>
			</li>
			{isLoaded && (
				<li>
				</li>
			)}
		</ul>
	);
}

export default Navigation;
