package furhatos.app.interviewrobot.flow
import furhatos.app.interviewrobot.nlu.*
import furhatos.nlu.common.*
import furhatos.flow.kotlin.*
import furhatos.gestures.Gestures

// initial state of the interaction
val Start : State = state(Interaction) {

    onEntry {
        furhat.ask({
            +"Hello!"
            + Gestures.Smile(duration = 2.0, strength = 2.0)
            +" How nice to see you. How are you today?" })
    }

    onResponse<BadAndYou>{
        furhat.gesture(Gestures.Oh(duration = 1.0, strength = 2.0), async = true)
        furhat.say("Oh, I am doing very well thank you, how kind of you to ask!")
        furhat.gesture(Gestures.BigSmile(duration = 1.8, strength = 1.8))
        furhat.say("I'm sorry to hear that you are not good.")
        goto(Problem)
    }
    onResponse<HowRU>{
        furhat.gesture(Gestures.Oh(duration = 1.0, strength = 2.0), async = true)
        furhat.say("Oh, I am doing very well thank you, how kind of you to ask!")
        furhat.gesture(Gestures.BigSmile(duration = 1.8, strength = 1.8))
        goto(Visitors)
    }


    onResponse<Good>{
        furhat.gesture(Gestures.BigSmile(duration = 1.8, strength = 1.5),async=true)
        furhat.say {
            +"That's good to hear!"
        }
        goto(Visitors)
    }
    onResponse<Bad>{
        furhat.gesture(Gestures.ExpressSad(duration = 2.0, strength = 1.0),async=true)
        furhat.say("I'm sorry to hear that.")
        goto(Problem)
    }
    onResponse<Hobby>{
        furhat.gesture(Gestures.Smile(duration = 1.8, strength = 1.8),async=true)
        furhat.say("${it.text}, how nice!" )
        goto(Visitors)
    }

}

// optional state, accessible through negative response
val Problem : State = state(Interaction){
    onEntry{
        furhat.gesture(Gestures.ExpressSad(duration = 2.0, strength = 1.0),async=true)
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

// optional state, accessible through problem state
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
// optional state, accessible through problem state
val Doctor : State = state(Interaction){
    onEntry{
        furhat.gesture(Gestures.ExpressSad(duration = 2.0, strength = 1.0),async=true)
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

// second state in core interaction
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

// branch state
val HadVisitors : State = state(Interaction){
    onEntry{
        furhat.ask("Did you have a good time?")
        furhat.gesture(Gestures.Smile(duration = 2.0, strength = 2.0))
    }
    onResponse<Yes>{
        furhat.gesture(Gestures.BigSmile(duration = 2.0, strength = 1.0),async=true)
        furhat.say("I am glad to hear that.")
        goto(Exercise)

    }
    onResponse<No>{
        furhat.gesture(Gestures.ExpressSad(duration = 2.0, strength = 1.0),async=true)
        furhat.say("Oh, I am sorry to hear that.")

        goto(Exercise)
    }
}

// branch state
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

// third state in core interaction
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
        furhat.gesture(Gestures.Smile(duration = 2.0, strength = 1.0),async=true)
        furhat.say("${it.text}, how nice! I hope it felt good.")
        goto(Sleep)
    }
}

// branch state
val WhatExercise: State = state(Interaction){
    onEntry{
        furhat.gesture(Gestures.Smile(duration = 2.0, strength = 2.0), async = true)
        furhat.ask("What did you do?")

    }
    onResponse{
        furhat.gesture(Gestures.BigSmile(duration = 2.0, strength = 1.0))
        furhat.say("That's nice! I'm glad to hear that you are staying active.")
        goto(Sleep)
    }
}

// branch state
val WalkWithStaff: State = state(Interaction){
    onEntry{
        furhat.ask("Would you like to take a walk with someone from the staff?")
        furhat.gesture(Gestures.Smile(duration = 2.0, strength = 2.0))
    }
    onResponse<Yes>{
        furhat.gesture(Gestures.Smile(duration = 3.5, strength = 2.0),async=true)
        furhat.say("How nice! I will let the staff know so that it can be arranged.")
        goto(Sleep)
    }
    onResponse<No>{
        furhat.say("Ok")
        goto(Sleep)
    }
}

// fourth state in core interaction
val Sleep : State = state(Interaction){
    onEntry{
        delay(1000)
        furhat.ask("Have you been sleeping well this last week?")
        furhat.gesture(Gestures.Smile(duration = 2.0, strength = 2.0))
    }
    onResponse<Yes>{
        furhat.gesture(Gestures.Smile(duration = 3.0, strength = 2.0),async=true)
        furhat.say("That's good to hear, sleep is important!")
        goto(Olympics)
    }
    onResponse<No>{
        goto(BadSleep)
    }
}

// branch state
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
        furhat.gesture(Gestures.Smile(duration = 2.0, strength = 1.0))
        goto(Olympics)
    }
}
// fifth state in core interaction
val Olympics : State = state(Interaction){
    onEntry{
        delay(1000)
        furhat.gesture(Gestures.Smile(duration = 2.0, strength = 2.0),async=true)
        furhat.ask("Did you watch the Olympics on TV?")
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

// branch state
val NoOlympics : State = state(Interaction){
    onEntry{
        furhat.ask("I see. Sweden did great, do you want to know how many medals Sweden won?")
        furhat.gesture(Gestures.Smile(duration = 2.0, strength = 2.0))
    }
    onResponse<Yes>{
        furhat.gesture(Gestures.BigSmile(duration = 2.0, strength = 1.0),async=true)
        furhat.say("Sweden won a total of 18 medals, 8 gold medals, 5 silver and 5 bronze.")
        goto(Food)
    }
    onResponse<No>{
        furhat.say("Ok")
        goto(Food)
    }
}

// sixth state in core interaction
val Food : State = state(Interaction){
    onEntry{
        delay(1000)
        furhat.ask("Have you liked the food here lately?")
        furhat.gesture(Gestures.Smile(duration = 2.0, strength = 2.0))
    }
    onResponse<Yes>{
        furhat.gesture(Gestures.Smile(duration = 3.0, strength = 1.5),async = true)
        furhat.say("I'm glad to hear that you liked it.")
        goto(Activity)
    }
    onResponse<No>{
        goto(BadFood)
    }
}

// branch state
val BadFood : State = state(Interaction){
    onEntry{
        furhat.gesture(Gestures.Oh(duration = 2.0, strength = 2.0),async=true)
        furhat.ask("I'm sorry to hear that you didn't like it! Does this affect your appetite?")
    }
    onResponse{
        goto(RequestMeal)
    }

}

// branch state
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

// branch state
val FoodOrder : State = state(Interaction){
    onEntry{
        furhat.ask("Ok. What would you like to have?")
        furhat.gesture(Gestures.Smile(duration = 2.0, strength = 1.0))
    }
}

// seventh state in core interaction
val Activity : State = state(Interaction){
    onEntry{
        delay(1000)
        furhat.gesture(Gestures.Smile(duration = 3.0, strength = 1.5),async=true)
        furhat.ask("What kind of activity would you like to do for this week?")
    }
    onResponse<RequestOptions> {
        furhat.say("We have ${Activity().optionsToText()}")
        furhat.gesture(Gestures.Smile(duration = 2.0, strength = 1.5),async=true)
        furhat.ask("Which one do you prefer?")
    }
    onResponse<Activity>{
        furhat.gesture(Gestures.BigSmile(duration = 2.0, strength = 1.0),async=true)
        furhat.say("Good choice! It will be brought to you later today.")
        goto(Game)
    }
    onResponse{
        furhat.say("I am not sure we can provide that. We have ${Activity().optionsToText()}")
        furhat.ask("Would you like any of these?")
        furhat.gesture(Gestures.Smile(duration = 2.0, strength = 1.0))
    }
    onResponse<None>{
        furhat.say("Ok, you will get the chance to pick a new one next week")
        goto(Game)
    }
}

// eighth state in core interaction
val Game : State = state(Interaction){
    onEntry{
        delay(1000)
        furhat.gesture(Gestures.Smile(duration = 3.0, strength = 1.5),async=true)
        furhat.ask("Would you be interested in playing a game with me later?")
    }
    onResponse<RequestOptions> {
        furhat.say("We have ${Game().optionsToText()}")
        furhat.ask("Which one do you prefer?")
        furhat.gesture(Gestures.Smile(duration = 2.0, strength = 2.0))
    }
    onResponse<Game>{
        furhat.gesture(Gestures.BigSmile(duration = 2.0, strength = 1.2),async=true)
        furhat.say("${it.text}, good choice! I am looking forward to it. You'd better bring your A-game")
        furhat.gesture(Gestures.Wink, async = true)
        furhat.gesture(Gestures.Smile)
        goto(EndInterview)
    }
    onResponse<Yes>{
        furhat.say("How fun! We have ${Game().optionsToText()}")
        furhat.gesture(Gestures.Smile(duration = 2.0, strength = 1.5),async=true)
        furhat.ask("Which one would you like to play?")
    }
    onResponse<No>{
        furhat.say("Ok, I will ask you again next week in case you have changed your mind.")
        goto(EndInterview)
    }
    onResponse{
        furhat.gesture(Gestures.ExpressSad(duration = 1.2, strength = 1.5))
        furhat.say("Unfortunately we do not have that game. We have ${Game().optionsToText()}")
        furhat.ask("Which one would you like to play?")
    }
}

// ninth and final state in core interaction
val EndInterview : State = state(Interaction){
    onEntry{
        delay(1000)
        furhat.say("We have now reached the end of this interview. If there is something specific " +
                "that you would like to talk about next time, please let the staff know. Until then, take care!")
        furhat.gesture(Gestures.BigSmile(strength = 1.0, duration = 3.0))
    }
}