import { ICategoryModel } from "./category";

export interface IProductModel {
  id: number;
  category: ICategoryModel;
  name: string;
  description: string;
  price: number;
  imageUrl: string;
}
