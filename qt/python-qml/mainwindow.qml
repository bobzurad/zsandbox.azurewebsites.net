import QtQuick 2.6
import QtQuick.Controls 1.4
import QtQuick.Window 2.2
//import QtApplicationManager 1.0   //this is currently throwing an error: module "QtApplicationManager" is not installed

//ApplicationManagerWindow {    // can't use this until ApplicationManager import is working
ApplicationWindow {
    id: mainWindow

    width:  320
    height:  240
    color: "white"
    visible: true

    Text {
        width: parent.width
        anchors {
            top: parent.top
            topMargin: 10
        }
        horizontalAlignment: Text.AlignHCenter
        text: view_model.current_value
        font.pointSize: 48
    }

    Button {
        id: upButton
        width: 100
        height: 50
        anchors {
            bottom: parent.bottom
            right: parent.right
            bottomMargin: 2
            rightMargin: 2
        }
        text: "+"
        onClicked: {
            view_model.increment()
        }
    }

    Button {
        id: downButton
        width: 100
        height: 50
        anchors {
            bottom: parent.bottom
            left: parent.left
            bottomMargin: 2
            leftMargin: 2
        }
        text: "-"
        onClicked: {
            view_model.decrement()
        }
    }    
}