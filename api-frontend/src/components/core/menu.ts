export interface MenuParent extends MenuChild{
    icon: string;
    childs:MenuChild[];
}

export interface MenuChild{
    description:string;
    link:string;
    active:boolean;
}

export const colaboradorMenu: MenuParent[] = [
    {
      icon: 'fas fa-home',
      description: 'Home',
      active: false,
      link: '/authorized',
      childs: []
    },
    {
      icon: 'fas fa-clock',
      description: 'Lançamento de Horas',
      active: false,
      link:'',
      childs: [
        { active: false, description: 'Lançamento', link: 'authorized/lancamentohoras' }
      ]
    },
    {
      icon: 'fa-solid fa-right-from-bracket',
      description: 'Sair',
      active: false,
      link:'/',
      childs: []
    }
]

export const gestorMenu: MenuParent[] = [
      {
        icon: 'fas fa-home',
        description: 'Home',
        active: false,
        link: '/authorized',
        childs: []
      },
      {
        icon: 'fas fa-clock',
        description: 'Lançamento de Horas',
        active: false,
        link:'',
        childs: [
          { active: false, description: 'Lançamento', link: 'authorized/lancamentohoras' }
        ]
      },
      {
        icon: 'fa-solid fa-right-from-bracket',
        description: 'Sair',
        active: false,
        link:'/',
        childs: []
      }
]

export const adminMenu: MenuParent[] = [
  {
    icon: 'fas fa-home',
    description: 'Home',
    active: false,
    link: '/authorized',
    childs: []
  },
  {
    icon: 'fa-solid fa-users',
    description: 'Controle',
    active: false,
    link:'',
    childs: [
      { active: false, description: 'CR', link: '/cr' },
      { active: false, description: 'Usuario', link: '/usuario' },
      { active: false, description: 'Cliente', link: '/cliente' },
    ]
  },
  {
    icon: 'fas fa-clock',
    description: 'Aprovação/Reprovação',
    active: false,
    link:'authorized/lancamentohoras',
    childs: []
  },
  {
    icon: 'fa-solid fa-right-from-bracket',
    description: 'Sair',
    active: false,
    link:'/',
    childs: []
  }
]
