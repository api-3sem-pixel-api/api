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

export default axiosInstance;