import QtQuick 2.6
import QtQuick.Window 2.2
import QtQuick.Controls 1.4

ApplicationWindow {
    id: mainWindow

    // propertys can be set directly in the qml file.
    width:  320
    height:  240
    color: "white"
    visible: true

    Text {
        anchors {
            top: parent.top
            left: parent.left
        }
        text: "hello world"
    }
}