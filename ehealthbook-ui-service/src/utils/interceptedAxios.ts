import axios from 'axios';
const interceptedAxios = axios.create();

// Interceptor for adding Authorization header to each request
interceptedAxios.interceptors.request.use(
  (config) => {

    // Extract token from the session storage value
    const token = null;

    // Add Authorization header if token is found
    if (token) {
      config.headers['Authorization'] = `Bearer ${token}`;
    }

    config.baseURL = 'http://localhost:8080/ehealthbook';

    return config;
  },
  (error) => {
    // Handle the error by returning a rejected promise
    return Promise.reject(error);
  }
);

export default interceptedAxios;
