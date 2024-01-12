import React, { Component } from 'react'
import PayloadDescriptorService from '../services/PayloadDescriptorService';

class UpdatePayloadDescriptorComponent extends Component {
    constructor(props) {
        super(props)

        this.state = {
            id: this.props.match.params.id,
                payloadType: '',
                objectType: ''
        }
        this.updatePayloadDescriptor = this.updatePayloadDescriptor.bind(this);

        this.changepayloadTypeHandler = this.changepayloadTypeHandler.bind(this);
        this.changeObjectTypeHandler = this.changeObjectTypeHandler.bind(this);
    }

    componentDidMount(){
        PayloadDescriptorService.getPayloadDescriptorById(this.state.id).then( (res) =>{
            let payloadDescriptor = res.data;
            this.setState({
                payloadType: payloadDescriptor.payloadType,
                objectType: payloadDescriptor.objectType
            });
        });
    }

    updatePayloadDescriptor = (e) => {
        e.preventDefault();
        let payloadDescriptor = {
            payloadDescriptorId: this.state.id,
            payloadType: this.state.payloadType,
            objectType: this.state.objectType
        };
        console.log('payloadDescriptor => ' + JSON.stringify(payloadDescriptor));
        console.log('id => ' + JSON.stringify(this.state.id));
        PayloadDescriptorService.updatePayloadDescriptor(payloadDescriptor).then( res => {
            this.props.history.push('/payloadDescriptors');
        });
    }

    changepayloadTypeHandler= (event) => {
        this.setState({payloadType: event.target.value});
    }
    changeObjectTypeHandler= (event) => {
        this.setState({objectType: event.target.value});
    }

    cancel(){
        this.props.history.push('/payloadDescriptors');
    }

    render() {
        return (
            <div>
                <br></br>
                   <div className = "container">
                        <div className = "row">
                            <div className = "card col-md-6 offset-md-3 offset-md-3">
                                <h3 className="text-center">Update PayloadDescriptor</h3>
                                <div className = "card-body">
                                    <form>
                                        <div className = "form-group">
                                            <label> payloadType: </label>
                                            #formFields( $attribute, 'update')
                                            <label> ObjectType: </label>
                                            #formFields( $attribute, 'update')
                                        </div>
                                        <button className="btn btn-success" onClick={this.updatePayloadDescriptor}>Save</button>
                                        <button className="btn btn-danger" onClick={this.cancel.bind(this)} style={{marginLeft: "10px"}}>Cancel</button>
                                    </form>
                                </div>
                            </div>
                        </div>

                   </div>
            </div>
        )
    }
}

export default UpdatePayloadDescriptorComponent
