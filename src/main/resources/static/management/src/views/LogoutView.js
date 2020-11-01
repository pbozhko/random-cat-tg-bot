import React, {useEffect} from 'react'
import {fakeAuth} from '../auth'
import {Redirect} from 'react-router-dom'

const LogoutView = () => {

    useEffect(() => {
        fakeAuth.logout()
    })
    return <Redirect to="/"/>
}

export default LogoutView
