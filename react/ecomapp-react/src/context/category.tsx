import { atom } from "jotai";
import { ICategoryModel } from "../models/category";

export const categoriesAtom = atom([] as ICategoryModel[]);
