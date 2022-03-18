package furhatos.app.interviewrobot.flow
import furhatos.flow.kotlin.*
import furhatos.flow.kotlin.voice.PollyNeuralVoice

val Idle: State = state {

    init {
        // set the face and voice of the robot and tweak end silence
        furhat.voice = PollyNeuralVoice.Amy().also { it.style = PollyNeuralVoice.Style.Neutral}
        furhat.param.endSilTimeout = 1000   // increased from default 800ms

        furhat.setTexture("Isabel")
        if (users.count > 0) {
            furhat.attend(users.random)
            goto(Start)
        }
    }

    onEntry {
        furhat.attendNobody()
    }

    onUserEnter {
        furhat.attend(it)
        goto(Start)
    }
}


val Interaction: State = state {

    onUserLeave(instant = true) {
        if (users.count > 0) {
            if (it == users.current) {
                furhat.attend(users.other)
                goto(Start)
            } else {
                furhat.glance(it)
            }
        } else {
            goto(Idle)
        }
    }

    onUserEnter(instant = true) {
        furhat.glance(it)
    }

}