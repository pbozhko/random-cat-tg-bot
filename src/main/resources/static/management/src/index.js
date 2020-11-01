import React from 'react'
import {render} from 'react-dom'
import {Provider} from 'react-redux'

import store from './store'

import AppView from './views/AppView'
import {BrowserRouter} from 'react-router-dom'

render(
    <BrowserRouter>
        <Provider store={store}>
            <AppView/>
        </Provider>
    </BrowserRouter>,
    document.getElementById('root')
)
