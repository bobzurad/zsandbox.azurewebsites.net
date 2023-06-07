import React from 'react';
import {PaperProvider} from 'react-native-paper';
import Navigator from './Navigator';

const App = (): JSX.Element => {
  // Note: wrap all other providers outside of PaperProvider

  return (
    <PaperProvider>
      <Navigator />
    </PaperProvider>
  );
};

export default App;
