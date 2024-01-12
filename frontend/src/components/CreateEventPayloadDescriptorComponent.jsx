import React, { Component } from 'react'
import EventPayloadDescriptorService from '../services/EventPayloadDescriptorService';

class CreateEventPayloadDescriptorComponent extends Component {
    constructor(props) {
        super(props)

        this.state = {
            // step 2
            id: this.props.match.params.id,
                payloadType: '',
                objectType: '',
                units: '',
                currency: ''
        }
        this.changepayloadTypeHandler = this.changepayloadTypeHandler.bind(this);
        this.changeObjectTypeHandler = this.changeObjectTypeHandler.bind(this);
        this.changeunitsHandler = this.changeunitsHandler.bind(this);
        this.changecurrencyHandler = this.changecurrencyHandler.bind(this);
    }

    // step 3
    componentDidMount(){

        // step 4
        if(this.state.id === '_add'){
            return
        }else{
            EventPayloadDescriptorService.getEventPayloadDescriptorById(this.state.id).then( (res) =>{
                let eventPayloadDescriptor = res.data;
                this.setState({
                    payloadType: eventPayloadDescriptor.payloadType,
                    objectType: eventPayloadDescriptor.objectType,
                    units: eventPayloadDescriptor.units,
                    currency: eventPayloadDescriptor.currency
                });
            });
        }        
    }
    saveOrUpdateEventPayloadDescriptor = (e) => {
        e.preventDefault();
        let eventPayloadDescriptor = {
                eventPayloadDescriptorId: this.state.id,
                payloadType: this.state.payloadType,
                objectType: this.state.objectType,
                units: this.state.units,
                currency: this.state.currency
            };
        console.log('eventPayloadDescriptor => ' + JSON.stringify(eventPayloadDescriptor));

        // step 5
        if(this.state.id === '_add'){
            eventPayloadDescriptor.eventPayloadDescriptorId=''
            EventPayloadDescriptorService.createEventPayloadDescriptor(eventPayloadDescriptor).then(res =>{
                this.props.history.push('/eventPayloadDescriptors');
            });
        }else{
            EventPayloadDescriptorService.updateEventPayloadDescriptor(eventPayloadDescriptor).then( res => {
                this.props.history.push('/eventPayloadDescriptors');
            });
        }
    }
    
    changepayloadTypeHandler= (event) => {
        this.setState({payloadType: event.target.value});
    }
    changeObjectTypeHandler= (event) => {
        this.setState({objectType: event.target.value});
    }
    changeunitsHandler= (event) => {
        this.setState({units: event.target.value});
    }
    changecurrencyHandler= (event) => {
        this.setState({currency: event.target.value});
    }

    cancel(){
        this.props.history.push('/eventPayloadDescriptors');
    }

    getTitle(){
        if(this.state.id === '_add'){
            return <h3 className="text-center">Add EventPayloadDescriptor</h3>
        }else{
            return <h3 className="text-center">Update EventPayloadDescriptor</h3>
        }
    }
    render() {
        return (
            <div>
                <br></br>
                   <div className = "container">
                        <div className = "row">
                            <div className = "card col-md-6 offset-md-3 offset-md-3">
                                {
                                    this.getTitle()
                                }
                                <div className = "card-body">
                                    <form>
                                        <div className = "form-group">
                                            <label> payloadType: </label>
                                            #formFields( $attribute, 'create')
                                            <label> ObjectType: </label>
                                            #formFields( $attribute, 'create')
                                            <label> units: </label>
                                            #formFields( $attribute, 'create')
                                            <label> currency: </label>
                                            #formFields( $attribute, 'create')
                                        </div>

                                        <button className="btn btn-success" onClick={this.saveOrUpdateEventPayloadDescriptor}>Save</button>
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

export default CreateEventPayloadDescriptorComponent
