import {LOAD_ALL_PHOTOS} from '../types'

const initialState = {
    items: []
}

const photosReducer = (state = initialState, action) => {
    switch (action.type) {
        case LOAD_ALL_PHOTOS:
            return {
                ...state,
                items: action.payload
            }
        default:
            return state
    }
}

export default photosReducer
