import React from 'react'
import {useDispatch, useSelector} from 'react-redux'
import {sortedRules} from '../actions/rulesAction'

const TablePage = () => {

    const columns = ["Name", "Company", "City", "State"]

    const dispatch = useDispatch()

    const AllRules = useSelector(state => state.data)
    const SortedRules = useSelector(state => state.sortedRules)

    const options = {
        filterType: 'checkbox',
        onTableChange: (action, tableState) => {
            dispatch(sortedRules(tableState.data))
        }
    };

    return (
        <React.Fragment>
            <table>
                <thead>
                <tr>{columns.map((column, index) => <td key={index}><b>{column}</b></td>)}</tr>
                </thead>
                <tbody>
                {
                    AllRules.map(
                        (rule, index) => <tr>
                            <td key={index + 0}>{rule[0]}</td>
                            <td key={index + 1}>{rule[1]}</td>
                            <td key={index + 2}>{rule[2]}</td>
                            <td key={index + 3}>{rule[3]}</td>
                        </tr>
                    )
                }
                </tbody>
            </table>
        </React.Fragment>
    )
}

export default TablePage
