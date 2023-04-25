import { createContext, SetStateAction, Dispatch, useState } from "react";
import { ICategoryModel } from "../models/category";

export type CategoryContextType = {
  categories: ICategoryModel[];
  updateCategories: (categories: ICategoryModel[]) => void;
};

export const CategoryContext = createContext<CategoryContextType | null>(null);

export const CategoryProvider = ({ children }: any) => {
  const [categories, setCategories] = useState<ICategoryModel[]>([]);

  const updateCategories = (updatedCategories: ICategoryModel[]) => {
    console.log(updatedCategories);
    setCategories(updatedCategories);
  };

  return (
    <CategoryContext.Provider value={{ categories, updateCategories }}>
      {children}
    </CategoryContext.Provider>
  );
};
