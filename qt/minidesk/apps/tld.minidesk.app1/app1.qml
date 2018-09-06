import QtQuick 2.4
import QtApplicationManager 1.0

ApplicationManagerWindow {
    id: root
    color: "peachpuff"

    Rectangle {
        anchors.centerIn: parent
        width: 180; height: 180; radius: width/4
        color: "peru"

        Image {
            source: ApplicationInterface.icon
            anchors.centerIn: parent
        }

        RotationAnimation on rotation {
            id: rotation
            from: 0; to: 360; loops: Animation.Infinite; duration: 4000
        }

        MouseArea {
            anchors.fill: parent
            onClicked: {
                if (rotation.paused) {
                    rotation.resume();
                } else {
                    rotation.pause();
                    root.setWindowProperty("rotation", parent.rotation);
                }
                popUp.visible = !popUp.visible;
            }
        }
    }

    ApplicationManagerWindow {
        id: popUp
        visible: false
        color: "lightcoral"

        Text {
            anchors.centerIn: parent
            text: "App1 paused!"
        }

        Component.onCompleted: setWindowProperty("type", "pop-up");
    }
}
