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
        { active: false, description: 'Lançamento', link: '/lancamentohoras' }
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
          { active: false, description: 'Lançamento', link: '/lancamentohoras' },
          { active: false, description: 'Aprovação/Reprovação', link: '/controlehoras' },
          { active: false, description: 'Cadastro Cliente', link: '/cliente' },
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
    ...gestorMenu
]
