import { computed, ref } from 'vue';
import router from '@/router/index';
import http from '@/services/http';

interface User {
  firstName: string;
  password: string; 

}

export const useAuth = () => {
  const token = ref<string | null>(localStorage.getItem('token'));
  const userJson = localStorage.getItem('user');
  const user = ref<User | null>(userJson ? JSON.parse(userJson) : null);
  const permissionLevel = ref<number>();
  const isAuth = ref(false);

  function setToken(tokenValue: string | null) {
    if (tokenValue) {
      localStorage.setItem('token', tokenValue);
    } else {
      localStorage.removeItem('token');
    }
    token.value = tokenValue;
    isAuth.value = !!tokenValue && !!user.value;
  }

  function setUser(userValue: User | null) {
    if (userValue) {
      localStorage.setItem('user', JSON.stringify(userValue));
    } else {
      localStorage.removeItem('user');
    }
    user.value = userValue;
    isAuth.value = !!token.value && !!userValue;
  }

  function getPermissionLevel() : string {
    return localStorage.getItem('permissionLevel') ?? ''
  }

  function setPermissionLevel(permission: number) {
    localStorage.setItem('permissionLevel', permission.toString());
    permissionLevel.value = permission;
  }

  function setIsAuth(auth: boolean) {
    isAuth.value = auth;
  }

  const isAuthenticated = computed(() => {
    return !!token.value && !!user.value;
  });

  const fullName = computed(() => {
    if (user.value) {
      return user.value.firstName + ' ' + user.value.password; 
    }
    return '';
  });

  async function checkToken() {
    try {
      if (token.value) {
        const tokenAuth = 'Bearer ' + token.value;
        const { data } = await http.get('/auth/verify', {
          headers: {
            Authorization: tokenAuth,
          },
        });

      } else {
        clear();
        router.push('/login');
      }
    } catch (error) {
      clear();
      router.push('/login');
      console.error('Erro ao verificar token', error);
    }
  }

  function clear() {
    localStorage.removeItem('token');
    localStorage.removeItem('user');
    localStorage.removeItem('permissionLevel');
    isAuth.value = false;
    token.value = null;
    user.value = null;
    permissionLevel.value = 0;
  }

  return {
    token,
    user,
    getPermissionLevel,
    setToken,
    setUser,
    setPermissionLevel,
    checkToken,
    isAuthenticated,
    fullName,
    clear,
    setIsAuth,
    isAuth,
  };
};
