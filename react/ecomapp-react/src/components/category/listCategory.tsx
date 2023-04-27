import { useAtomValue } from "jotai";
import { categoriesAtom } from "../../context/category";

export default function ListCategory() {
  const categories = useAtomValue(categoriesAtom);

  return (
    <>
      <div>Categories</div>
      {categories.length === 0 ? (
        <>
          <div>There are no categories.</div>
        </>
      ) : (
        <>
          {categories.map((category) => {
            return (
              <div key={category.id}>
                <div>{category.categoryName}</div>
                <div>{category.description}</div>
              </div>
            );
          })}
        </>
      )}
    </>
  );
}
