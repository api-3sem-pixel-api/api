import { useAuth } from '@/stores/auth';
import axios, { AxiosInstance } from 'axios';


const headers = function(){
  const auth = useAuth().getUser();
  if(auth == undefined || auth == null) return {};
  return { 
    Authorization: 'Bearer ' + auth.token
  }
};

const axiosInstance: AxiosInstance = axios.create({
  baseURL: 'http://localhost:8080'
});

export default axiosInstance;
