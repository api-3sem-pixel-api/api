export interface MenuParent extends MenuChild{
    icon: string;
    childs:MenuChild[];
}

export interface MenuChild{
    description:string;
    link:string;
    active:boolean;
}