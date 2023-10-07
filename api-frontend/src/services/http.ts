import { useAuth } from '@/stores/auth';
import axios, { AxiosInstance } from 'axios';

const headers = function(){
  const auth = useAuth().getUser();
  if(auth == undefined || auth == null || auth.token == undefined) return {};
  return { 
    Authorization: 'Bearer ' + auth.token
  }
};

const axiosInstance: AxiosInstance = axios.create({
  baseURL: 'http://localhost:8080',
  headers: headers()
});

axiosInstance.interceptors.request.use(function (config) {
  const el = document.getElementById('loading-interceptor');
  el!.style.display = "block";
  return config;
}, function (error) {
  const el = document.getElementById('loading-interceptor');
  // el!.style.display = "block";
  return Promise.reject(error);
});

axiosInstance.interceptors.response.use(function (response) {
  const el = document.getElementById('loading-interceptor');
  el!.style.display = "none";
  return response;
}, function (error) {
  const el = document.getElementById('loading-interceptor');
  el!.style.display = "none";
  return Promise.reject(error);
});

export default axiosInstance;
