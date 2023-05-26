import { atom } from "jotai";
import { IProductModel } from "../models/product";

export const productsAtom = atom([] as IProductModel[]);
