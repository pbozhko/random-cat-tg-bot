import axios from 'axios'
import {LOAD_ALL_PHOTOS, LOAD_ALL_PHOTOS_ERROR} from '../types'

export const loadAllPhotos = () => async dispatch => {
    try {
        const response = await axios.get(window.env.API_DOMAIN_ADDR + '/api/management/v1/photos')
        dispatch({
            type: LOAD_ALL_PHOTOS,
            payload: response.data
        })
    } catch (e) {
        console.log(e)
        dispatch({
            type: LOAD_ALL_PHOTOS_ERROR,
            payload: []
        })
    }
}
