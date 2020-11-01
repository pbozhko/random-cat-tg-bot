import React from 'react'
import {render} from 'react-dom'
import {Provider} from 'react-redux'

import store from './store'

import App from './view/App'
import {BrowserRouter} from 'react-router-dom'

render(
    <BrowserRouter>
        <Provider store={store}>
            <App/>
        </Provider>
    </BrowserRouter>,
    document.getElementById('root')
)
