import React, { Component } from 'react';
var axios = require('axios'); //for some reason, I couldn't get it to work with webpack, i.e. import axios from 'axios'; failed.

axios.interceptors.request.use(request => {
  console.log('Starting Request', request);
  return request
});

class Codathon extends Component {

  constructor(props) {
    super(props);
    this.state = {code: '', evaluationResult: ''};

    this.changed = this.changed.bind(this);
    this.submit = this.submit.bind(this);
  }

  changed = (event) => {
    this.setState({code: event.target.value, evaluationResult: this.state.evaluationResult})
  };

  submit = (event) => {
    var body = {
      code: this.state.code
    };
    axios({
      method: 'post',
      url: 'http://localhost:8080/api/v0/evaluation',
      data: body,
      headers: {
        "Content-Type":"application/json",
        "Accept": "application/json"
      }
    }).then(res => {
      alert("res: " + res.data.body);
      this.setState({code: this.state.code, evaluationResult: res.data.body});
    }).catch(err => {
      alert("Error: " + err);
    });
  };

  render() {
    return (
      <div>
        <form>
          <div>
            <label>
              Code:
            </label>
          </div>
          <div>
            <textarea rows={10} cols={50}
              defaultValue={this.state.code} onChange={this.changed}></textarea>
          </div>
          <div>
            <input type="submit" onClick={this.submit}/>
          </div>
        </form>
        <label>Result: {this.state.evaluationResult}</label>
      </div>
    )
  }
}

export default Codathon;
