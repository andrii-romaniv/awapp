import axios from 'axios'

const AWAPP_API_URL = 'http://localhost:8084'
const BOOKING_API_URL = `${AWAPP_API_URL}/bookings/`
class BookingDataService {

    retrieveAllCourses(name) {
        //console.log('executed service')
        return axios.get(`${BOOKING_API_URL}`);
    }
}

export default new BookingDataService()