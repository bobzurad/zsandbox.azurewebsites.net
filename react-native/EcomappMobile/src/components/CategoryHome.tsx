import * as React from 'react';
import {Text, List} from 'react-native-paper';
import fetchClient from '../api/index';
import {ICategoryModel} from '../models/category';

const CategoryHome = () => {
  const [categories, setCategories] = React.useState([] as ICategoryModel[]);

  React.useEffect(() => {
    refreshCategories();
  }, []);

  const refreshCategories = () => {
    fetchClient
      .get('/category/')
      .then(response => {
        setCategories(response.data as ICategoryModel[]);
      })
      .catch(error => {
        console.log(error);
      });
  };

  return (
    <>
      <Text>Categories</Text>
      {categories.length > 0 &&
        categories.map(category => {
          return (
            <List.Item
              key={category.id}
              title={category.categoryName}
              description={category.description}
            />
          );
        })}
    </>
  );
};

export default CategoryHome;
