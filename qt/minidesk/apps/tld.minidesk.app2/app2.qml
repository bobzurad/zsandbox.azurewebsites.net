import QtQuick 2.4
import QtApplicationManager 1.0

ApplicationManagerWindow {
    color: ApplicationInterface.systemProperties.light ? "peachpuff" : "black"

    Image {
        anchors.centerIn: parent
        source: ApplicationInterface.icon

        MouseArea {
            anchors.fill: parent
            onClicked: {
                var notification = ApplicationInterface.createNotification();
                notification.summary = "Let there be light!"
                notification.show();
            }
        }
    }

    onWindowPropertyChanged: console.log("App2: onWindowPropertyChanged - " + name + ": " + value);

    Connections {
        target: ApplicationInterface
        onOpenDocument: console.log("App2: onOpenDocument - " + documentUrl);
        onQuit: target.acknowledgeQuit();
    }

    ApplicationInterfaceExtension {
        id: extension
        name: "tld.minidesk.interface"

        onReadyChanged: console.log("App2: circumference function returned: "
                              + object.circumference(2.0, "plate") + ", it used pi = " + object.pi);
    }

    Connections {
        target: extension.object
        onComputed: console.log("App2: " + what + " has been computed");
        onPiChanged: console.log("App2: pi changed: " + target.pi);
    }
}
