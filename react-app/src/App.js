import React, { Component } from 'react';
import Clock from './components/clock';
import Toggle from './components/toggle';
import Toggle2 from './components/toggle2';
import logo from './logo.svg';
import './App.css';

class App extends Component {
  render() {
    return (
      <div className="App">
        <div className="App-header">
          <img src={logo} className="App-logo" alt="logo" />
          <h2>Welcome to React</h2>
        </div>
        <p className="App-intro">
          To get started, edit <code>src/App.js</code> and save to reload.
        </p>
        <Clock />
        <p>
          <Toggle />
        </p>
        <p>
          <Toggle2 />
        </p>
      </div>
    );
  }
}

export default App;
