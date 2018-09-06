
import QtQuick 2.4

Item {
    anchors.fill: parent

    Column {
        width: paragraph.implicitWidth
        height: heading.implicitHeight + paragraph.implicitHeight + 80
        anchors.centerIn: parent
        spacing: 10

        Text {
            id: heading
            color: "cornflowerblue"
            font { pixelSize: 20; weight: Font.Bold }
            text: "Minimal Desktop System-UI in pure QML"
        }

        Text {
            id: paragraph
            color: "grey"
            font.pixelSize: 16
            text: "The following features are supported:\n" +
                  "\u2022 Start applications by clicking an icon on the top left\n" +
                  "\u2022 Stop applications by clicking on the top/left window decoration rectangle\n" +
                  "\u2022 Raise applications by clicking on the decoration\n" +
                  "\u2022 Drag windows by pressing on the window decoration and moving\n" +
                  "\u2022 System-UI sends 'propA' change when an app is started\n" +
                  "\u2022 System-UI and App2 react on window property changes with a debug message\n" +
                  "\u2022 App1 animation can be stopped and restarted by a click\n" +
                  "\u2022 App1 sends 'rotation' property to System-UI on stop\n" +
                  "\u2022 App1 shows a pop-up on the System-UI while it is paused\n" +
                  "\u2022 App2 will make use of an IPC extension when it is started\n" +
                  "\u2022 App2 logs the document URL it has been started with\n" +
                  "\u2022 App2 triggers a notification in the System-UI when the bulb icon is clicked\n" +
                  "\u2022 A separate (\"wayland\") process started outside of appman will be shown as App1"
        }
    }
}