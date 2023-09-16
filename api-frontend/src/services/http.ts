import axios, { AxiosInstance } from 'axios';

const axiosInstance: AxiosInstance = axios.create({
  baseURL: 'http://localhost:8080',
  headers: {
    'Content-type': 'application/json',
    'Access-Control-Allow-Origin': '*',
  }
});

export default axiosInstance;
