import React from 'react';
import './warning.css';

function WarningBanner(props) {
  if (!props.warn) {
    return null;
  }

  return (
    <div className="warning">
      Warning!
    </div>
  );
}  

class Warning extends React.Component {
  constructor(props) {
    super(props);
    this.state = {showWarning: false}
    this.handleToggleClick = this.handleToggleClick.bind(this);
  }  

  handleToggleClick() {
    this.setState(prevState => ({
      showWarning: !prevState.showWarning
    }));
  }
  
  render() {
    return (
      <div>
        <WarningBanner warn={this.state.showWarning} />
        <button onClick={this.handleToggleClick}>
          {this.state.showWarning ? 'Hide Warning' : 'Show Warning'}
        </button>
      </div>
    );
  }  
}

export default Warning;