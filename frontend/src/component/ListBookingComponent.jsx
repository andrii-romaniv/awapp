import React, { Component } from 'react'

class ListBookingComponent extends Component {

    render() {
        return (
            <div className="container">
                <h3>All Bookings</h3>
                <div className="container">
                    <table className="table">
                        <thead>
                        <tr>
                            <th>Id</th>
                            <th>Description</th>
                        </tr>
                        </thead>
                        <tbody>
                        <tr>
                            <td>1</td>
                            <td>Monday, Haircuutter, 08:30</td>
                        </tr>
                        </tbody>
                    </table>
                </div>
            </div>
        )
    }
}

export default ListBookingComponent