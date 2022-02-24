package furhatos.app.interviewrobot.flow

import furhatos.app.interviewrobot.nlu.*
import furhatos.nlu.common.*
import furhatos.flow.kotlin.*
import furhatos.gestures.Gestures

val Start : State = state(Interaction) {

    onEntry {
//        furhat.gesture(Gestures.Smile(duration = 5.0, strength = 2.0))
        furhat.ask({
            +"Hello!"
            + Gestures.Smile(duration = 2.0, strength = 2.0)
            +" How nice to see you. How are you today?" })
    }

//    onInterimResponse(endSil = 500) {
//        // We give some feedback to the user, "okay" or a nod gesture.
//        random (
//            // Note that we need to set async = true, since we are in an instant trigger
//            { furhat.say("okay", async = true) },
//            // Gestures are async per default, so no need to set the flag
//            { furhat.gesture(Gestures.Nod) }
//        )
//    }

    onResponse<Good>{
        furhat.say {
            +"That's good to hear!"
            +Gestures.Smile(duration = 5.0, strength = 2.0)
        }
        goto(Visitors)
    }

    onResponse<Bad>{
        furhat.gesture(Gestures.ExpressSad(duration = 3.0, strength = 1.0))
        furhat.say("I'm sorry to hear that.")
        goto(Problem)
    }
    onResponse<Hobby>{
        furhat.say("${it.text}, how nice!" )
        goto(Visitors)
    }
}

val Problem : State = state(Interaction){
    onEntry{
        furhat.ask("What is wrong?")
    }
    onResponse<Pain>{
        goto(Doctor)
    }
    onResponse<Lonely>{
        goto(Family)
    }
    onResponse<Tired>{
        furhat.say{
            +"I see. I hope you feel better soon."
        }
            goto(Visitors)
        }
    }

val Family : State = state(Interaction){
    onEntry{
        furhat.ask("I see. Would you like to call your family?")
    }
    onResponse<Yes>{
        furhat.say("I will schedule a phone call for you for later today.")
        furhat.gesture(Gestures.Smile(duration = 2.0, strength = 2.0))
        goto(Visitors)
    }
    onResponse<No>{
        furhat.say{
            +"Ok."
        }
        goto(Visitors)
    }
}

val Doctor : State = state(Interaction){
    onEntry{
        furhat.ask("I see. Would you like to see a doctor?")
    }
    onResponse<Yes>{
        furhat.say{
            +"I will schedule an appointment for you for later today."
            +Gestures.Smile(duration = 2.0, strength = 2.0)
        }
        goto(Visitors)
    }
    onResponse<No>{
        furhat.say("Ok. I hope you feel better soon!")
        goto(Visitors)
    }
}

val Visitors : State = state(Interaction){
    onEntry{
        delay(1000)
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
        furhat.gesture(Gestures.Smile(duration = 2.0, strength = 2.0))
    }
    onResponse<Yes>{
        furhat.say("I am glad to hear that.")
        furhat.gesture(Gestures.Smile(duration = 2.0, strength = 2.0))
        goto(Exercise)

    }
    onResponse<No>{
        furhat.say("Oh, I am sorry to hear that.")
        furhat.gesture(Gestures.Oh(duration = 2.0, strength = 2.0))
        goto(Exercise)
    }
}

val HadNoVisitors : State = state(Interaction){
    onEntry{
        furhat.ask("Would you like to have visits more often?")
        furhat.gesture(Gestures.Smile(duration = 2.0, strength = 2.0))
    }
    onResponse<Yes>{
        furhat.say("I will let the members of staff know so that they can contact your family.")
        furhat.gesture(Gestures.Smile(duration = 2.0, strength = 2.0))
        goto(Exercise)

    }
    onResponse<No>{
        furhat.say("Ok.")
        goto(Exercise)
    }
}

val Exercise : State = state(Interaction){
    onEntry{
        delay(1000)
        furhat.ask("Did you exercise this week?")
    }
    onResponse<Yes>{
        goto(WhatExercise)
    }
    onResponse<No>{
        goto(WalkWithStaff)
    }
    onResponse<Exercises>{
        furhat.say("${it.text}, how nice! I hope it felt good.")
        goto(Sleep)
    }
}

val WhatExercise: State = state(Interaction){
    onEntry{
        furhat.ask("What did you do?")
        furhat.gesture(Gestures.Smile(duration = 2.0, strength = 2.0))
    }
    onResponse{
        furhat.say("That's nice! I'm glad to hear that you are staying active.")
        furhat.gesture(Gestures.Smile(duration = 2.0, strength = 2.0))
        goto(Sleep)
    }
}

val WalkWithStaff: State = state(Interaction){
    onEntry{
        furhat.ask("Would you like to take a walk with someone from the staff?")
        furhat.gesture(Gestures.Smile(duration = 2.0, strength = 2.0))
    }
    onResponse<Yes>{
        furhat.say("How nice! I will let the staff know so that it can be arranged.")
        furhat.gesture(Gestures.Smile(duration = 2.0, strength = 2.0))
        goto(Sleep)
    }
    onResponse<No>{
        furhat.say("Ok")
        goto(Sleep)
    }
}

val Sleep : State = state(Interaction){
    onEntry{
        delay(1000)
        furhat.ask("Have you been sleeping well this last week?")
        furhat.gesture(Gestures.Smile(duration = 2.0, strength = 2.0))
    }
    onResponse<Yes>{
        furhat.say("That's good to hear, sleep is important!")
        furhat.gesture(Gestures.Smile(duration = 2.0, strength = 2.0))
        goto(Olympics)
    }
    onResponse<No>{
        goto(BadSleep)
    }
}

val BadSleep : State = state(Interaction){
    onEntry{
        furhat.ask("Do you think it is temporary or should I contact the " +
                "manager so we can figure out how to best help you?")
    }
    onResponse<Yes>{
        furhat.say("Okay, I will let the manager know about your problems, good sleep is very important. ")
        goto(Olympics)
    }
    onResponse<No>{
        furhat.say("Okay, I hope it won't be a long lasting problem. If so, you can always tell me next time or contact a member of staff. It is important to sleep well. ")
        goto(Olympics)
    }
}

val Olympics : State = state(Interaction){
    onEntry{
        delay(1000)
        furhat.ask("Did you watch the Olympics on TV?")
        furhat.gesture(Gestures.Smile(duration = 2.0, strength = 2.0))
    }
    onResponse<Yes>{
        furhat.gesture(Gestures.BrowRaise(duration = 2.0, strength = 2.0))
        furhat.say("It was a great one right? Sweden won a total of 18 medals, 8 gold medals, 5 silver and 5 bronze.")
        furhat.gesture(Gestures.Smile(duration = 2.0, strength = 2.0))
        goto(Food)
    }
    onResponse<No>{
        goto(NoOlympics)
    }
}

val NoOlympics : State = state(Interaction){
    onEntry{
        furhat.ask("Do you want to know how many medals Sweden won?")
        furhat.gesture(Gestures.Smile(duration = 2.0, strength = 2.0))
    }
    onResponse<Yes>{
        furhat.say("Sweden won a total of 18 medals, 8 gold medals, 5 silver and 5 bronze.")
        furhat.say("Isn't that great?")
        goto(Food)
    }
    onResponse<No>{
        furhat.say("Ok")
        goto(Food)
    }
}

val Food : State = state(Interaction){
    onEntry{
        delay(1000)
        furhat.ask("Anyway.. Have you liked the food here lately?")
        furhat.gesture(Gestures.Smile(duration = 2.0, strength = 2.0))
    }
    onResponse<Yes>{
        furhat.say("I'm glad to hear that you liked it.")
        furhat.gesture(Gestures.Smile(duration = 2.0, strength = 2.0))
        goto(Activity)
    }
    onResponse<No>{
        goto(BadFood)
    }
}

val BadFood : State = state(Interaction){
    onEntry{
        furhat.ask("I'm sorry to hear that you didn't like it! Does this affect your appetite?")
        furhat.gesture(Gestures.Oh(duration = 2.0, strength = 2.0))
    }
    onResponse{
        goto(RequestMeal)
    }

}

val RequestMeal : State = state(Interaction){
    onEntry{
        furhat.ask("I see. Would you like to request any specific meal?")
        furhat.gesture(Gestures.Smile(duration = 2.0, strength = 2.0))
    }
    onResponse<Yes>{
        goto(FoodOrder)
    }
    onResponse<No>{
        furhat.say("Ok.")
        goto(Activity)
    }
}

val FoodOrder : State = state(Interaction){
    onEntry{
        furhat.ask("Ok. What would you like to have?")
        furhat.gesture(Gestures.Smile(duration = 2.0, strength = 2.0))
    }
    onResponse{
        furhat.say("I will let the cook know. I hope you will have it soon.")
        furhat.gesture(furhatos.gestures.Gestures.Smile(duration = 2.0, strength = 2.0))
        goto(Activity)
    }

}

val Activity : State = state(Interaction){
    onEntry{
        delay(1000)
        furhat.ask("What kind of activity would you like to do for this week?")
    }
    onResponse<RequestOptions> {
        furhat.say("We have ${Activity().optionsToText()}")
        furhat.ask("Which one do you prefer?")
    }
    onResponse<Activity>{
        furhat.say("Good choice! It will be brought to you later today.")
        goto(Game)
    }
    onResponse{
        furhat.say("I am not sure we can provide that. We have ${Activity().optionsToText()}")
        furhat.ask("Would you like any of these?")
    }
    onResponse<None>{
        furhat.say("Ok, you will get the chance to pick a new one next week")
        goto(Game)
    }
}


val Game : State = state(Interaction){
    onEntry{
        delay(1000)
        furhat.ask("Would you be interested in playing a game with me later?")
    }
    onResponse<RequestOptions> {
        furhat.say("We have ${Game().optionsToText()}")
        furhat.ask("Which one do you prefer?")
    }
    onResponse<Game>{
        furhat.say("${it.text}, good choice! I am looking forward to it. You'd better bring your A-game")
        furhat.gesture(Gestures.Wink)
        goto(EndInterview)
    }
    onResponse<Yes>{
        furhat.say("How fun! We have ${Game().optionsToText()}")
        furhat.ask("Which one would you like to play?")
    }
    onResponse<No>{
        furhat.say("Ok, I will ask you again next week in case you have changed your mind.")
        goto(EndInterview)
    }
    onResponse{
        furhat.say("Unfortunately we do not have that game. We have ${Game().optionsToText()}")
        furhat.ask("Which one would you like to play?")
    }
}


val EndInterview : State = state(Interaction){
    onEntry{
        delay(1000)
        furhat.say("We have now reached the end of this interview. If there is something specific " +
                "that you would like to talk about next time, please let the staff know. Until then, take care!")
        furhat.gesture(Gestures.Smile(strength = 2.0, duration = 3.0))
    }

}