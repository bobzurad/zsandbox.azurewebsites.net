import { useAtomValue } from "jotai";
import { List } from "antd";
import { categoriesAtom } from "../../context/category";
import ListCategoryItem from "./listCategoryItem";

export default function ListCategory() {
  const categories = useAtomValue(categoriesAtom);

  return (
    <>
      <h2>Categories</h2>
      {categories.length === 0 ? (
        <>
          <div>There are no categories.</div>
        </>
      ) : (
        <List
          dataSource={categories}
          renderItem={(category) => <ListCategoryItem category={category} />}
        />
      )}
    </>
  );
}
