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
          { active: false, description: 'Aprovação/Reprovação', link: '/controlehoras' }
        ]
      }
]

export const adminMenu: MenuParent[] = [
    ...gestorMenu
]