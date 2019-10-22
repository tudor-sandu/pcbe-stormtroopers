import React from 'react'
import { Button, Header, Icon, Modal } from 'semantic-ui-react'

const ModalGeneric = ({modalTrigger, modalTitle, modalMsg, modalAction, modalIcon}) => (
  <Modal trigger={modalTrigger} size='small'>
    <Header icon={modalIcon} content={modalTitle} />
    <Modal.Content>
      <p>
       {modalMsg}
      </p>
    </Modal.Content>
    <Modal.Actions>
      <Button onClick={modalAction} color='green' inverted>
        <Icon name='checkmark' /> Yes
      </Button>
    </Modal.Actions>
  </Modal>
)

export default ModalGeneric