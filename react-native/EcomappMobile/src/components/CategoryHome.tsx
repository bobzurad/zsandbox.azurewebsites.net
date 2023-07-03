import * as React from 'react';
import {StyleSheet} from 'react-native';
import {FAB} from 'react-native-paper';
import fetchClient from '../api/index';
import {ICategoryModel} from '../models/category';
import CategoryList from './CategoryList';

const styles = StyleSheet.create({
  fab: {
    position: 'absolute',
    margin: 16,
    right: 0,
    bottom: 0,
  },
});

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
      <CategoryList categories={categories} />
      <FAB
        icon="plus"
        style={styles.fab}
        onPress={() => console.log('Pressed')}
      />
    </>
  );
};

export default CategoryHome;
