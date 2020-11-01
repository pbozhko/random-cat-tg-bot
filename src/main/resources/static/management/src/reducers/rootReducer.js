import initialState from '../initialState';

const rootReducer = (state = initialState, action) => {
    switch (action.type) {
        case 'SORT':
            return {
                sortedRules: action.payload
            }
        default:
            return state
    }
};

export default rootReducer;
