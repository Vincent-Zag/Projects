const GET_DETAILS = "STOCKDETAILS/GET_DETAILS";
const DELETE_DETAILS = "STOCKDETAILS/DELETE_DETAILS"
const EDIT_DETAILS = "STOCKDETAILS/EDIT_DETAILS";



const getStockDetails = (stock) => ({
  type: GET_DETAILS,
  payload: stock,
});

export const deleteStockDetails = () => ({
  type: DELETE_DETAILS
});

export const editStockDetails = (stock) => ({
  type: EDIT_DETAILS,
  payload: stock
});


export const fetchStockDetails = (id) => async (dispatch) => {
  const response = await fetch(`/api/stocks/${id}`);
  if (response.ok) {
    const stock = await response.json();
    dispatch(getStockDetails(stock));
    return response;
  }
};

const initialState = {};

export default function reducer(state = initialState, action) {
  let newState;
  switch (action.type) {
    case GET_DETAILS:
      newState = action.payload;
      return newState;
    case DELETE_DETAILS:
        newState = {}
        return newState
    default:
      return state;
  }
}
