import React, { Component } from 'react';
import ListBookingComponent from './ListBookingComponent';
import { BrowserRouter as Router, Route, Switch } from 'react-router-dom'
// import BookingComponent from './BookingComponent';

class AwApp extends Component {
    render() {
        return (
            <Router>
                    <h1>Application for booking cosmetic procedures</h1>
                    <Switch>
                        <Route path="/" exact component={ListBookingComponent} />
                        <Route path="/bookings" exact component={ListBookingComponent} />
                        {/*<Route path="/bookings/:id" component={BookingComponent} />*/}
                    </Switch>
            </Router>
        )
    }
}
export default AwApp