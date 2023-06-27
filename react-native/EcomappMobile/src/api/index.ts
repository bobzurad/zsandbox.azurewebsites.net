import axios from 'axios';
import {API_BASE_URL} from '../globals/constants';

const fetchClient = () => {
  const defaultOptions = {
    baseURL: API_BASE_URL,
    headers: {
      'Content-Type': 'application/json',
      'X-Requested-With': 'XMLHttpRequest',
    },
  };

  return axios.create(defaultOptions);
};

export default fetchClient();
