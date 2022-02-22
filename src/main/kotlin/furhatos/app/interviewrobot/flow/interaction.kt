package furhatos.app.interviewrobot.flow

import furhatos.app.interviewrobot.nlu.*
import furhatos.nlu.common.*
import furhatos.flow.kotlin.*
import furhatos.gestures.Gestures

val Start : State = state(Interaction) {

    onEntry {
        furhat.ask("Hello! How nice to see you. How are you today?")
    }

    onResponse<Good>{
        furhat.say("Fantastic!")
        furhat.gesture(Gestures.BigSmile(duration = 2.0, strength = 1.0))
        goto(Visitors)
    }

    onResponse<Bad>{
        furhat.say("I'm sorry to hear that.")
        goto(Problem)
    }
}

val Problem : State = state(Interaction){
    onEntry{
        furhat.ask("What is wrong?")
        furhat.gesture(Gestures.Oh)
    }
    onResponse<Pain>{
        goto(Doctor)
    }
    onResponse<Lonely>{
        goto(Family)
    }
    onResponse<Tired>{
        furhat.ask("I see.")
        goto(Visitors)
    }
}

val Family : State = state(Interaction){
    onEntry{
        furhat.ask("I see. Would you like to call your family?")
    }
    onResponse<Yes>{
        furhat.say("I will schedule a phone call for you for later today.")
        furhat.gesture(Gestures.Smile)
        goto(Visitors)
    }
    onResponse<No>{
        furhat.say("Ok.")
        goto(Visitors)
    }
}

val Doctor : State = state(Interaction){
    onEntry{
        furhat.ask("I see. Would you like to see a doctor?")
    }
    onResponse<Yes>{
        furhat.say("I will schedule an appointment for you for later today.")
        furhat.gesture(Gestures.Smile)
        goto(Visitors)
    }
    onResponse<No>{
        furhat.say("Ok")
        goto(Visitors)
    }
}

val Visitors : State = state(Interaction){
    onEntry{
        furhat.ask("Have you had any visitors or spoken to family lately?")
    }
    onResponse<Yes>{
        goto(HadVisitors)
    }
    onResponse<No>{
        goto(HadNoVisitors)
    }
}

val HadVisitors : State = state(Interaction){
    onEntry{
        furhat.ask("Did you have a good time?")
        furhat.gesture(Gestures.Smile)
    }
    onResponse<Yes>{
        furhat.say("I am glad to hear that.")
        furhat.gesture(Gestures.Smile)
        goto(Olympics)

    }
    onResponse<No>{
        furhat.say("Oh, I am sorry to hear that.")
        furhat.gesture(Gestures.Oh)
        goto(Olympics)
    }
}

val HadNoVisitors : State = state(Interaction){
    onEntry{
        furhat.ask("Would you like to have visits more often?")
        furhat.gesture(Gestures.Smile)
    }
    onResponse<Yes>{
        furhat.say("I will let the members of staff know so that they can contact your family.")
        furhat.gesture(Gestures.Smile)
        goto(Olympics)

    }
    onResponse<No>{
        furhat.say("Ok.")
        goto(Olympics)
    }
}

val Olympics : State = state(Interaction){
    onEntry{
        furhat.ask("Did you watch the Olympics on TV?")
        furhat.gesture(Gestures.Smile)
    }
    onResponse<Yes>{
        furhat.say("It was a great one right? Sweden won a total of 18 medals, 8 gold medals, 5 silver and 5 bronze.")
        furhat.gesture(Gestures.Smile)
        goto(Food)
    }
    onResponse<No>{
        goto(NoOlympics)
    }

}

val NoOlympics : State = state(Interaction){
    onEntry{
        furhat.ask("Do you want to know how many medals sweden won?")
        furhat.gesture(Gestures.Smile)
    }
    onResponse<Yes>{
        furhat.say("Sweden won a total of 18 medals, 8 gold medals, 5 silver and 5 bronze.")
        goto(Food)
    }
    onResponse<No>{
        furhat.say("Ok")
        goto(Food)
    }

}

val Food : State = state(Interaction){
    onEntry{
        furhat.ask("Have you liked the food here lately?")
        furhat.gesture(Gestures.Smile)
    }
    onResponse<Yes>{
        furhat.say("I'm glad to hear that you liked it.")
        furhat.gesture(Gestures.Smile)
    }
    onResponse<No>{
        goto(BadFood)
    }

}

val BadFood : State = state(Interaction){
    onEntry{
        furhat.say("I'm sorry to hear that you did not like it!")
        furhat.gesture(Gestures.Oh)
        furhat.ask("What would be something you would like to eat?")
        furhat.gesture(Gestures.Smile)
    }
    onResponse{
        furhat.say("I will let the cook know. I hope you will have it soon.")
        furhat.gesture(Gestures.Smile)
    }

}