package furhatos.app.interviewrobot

import furhatos.app.interviewrobot.flow.*
import furhatos.skills.Skill
import furhatos.flow.kotlin.*

class InterviewrobotSkill : Skill() {
    override fun start() {
        Flow().run(Idle)
    }
}

fun main(args: Array<String>) {
    Skill.main(args)
}
