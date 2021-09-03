package game

import scala.collection.mutable.ArrayBuffer

/**
 *  Creator:    Sean Horner
 *  Created:    02.06.2021
 *  Updated:    02.09.2021
 *  Purpose:    This is the code for a simple math game where the user must guess a
 *              combination of randomly generated integers (increasing in range and
 *              number as the player succeeds).
 *  Require:    none
 **/

object number_guess_game extends App {
  var nums = new ArrayBuffer[Int]()
  var guesses = new ArrayBuffer[Int]()

  var level: Int = 1
  var sum: Int = 0
  var prod: Int = 1
  var guess: Int = 0
  var i: Int = 0

  var c: Char = 'y'

  var proceed: Boolean = true

  val numGenerator = scala.util.Random

  // Initial formatting spaces
  println("\n")

  // Game loop
  do {
    // Print initial level description
    game(level)

    // Picking random numbers for level
    i = 0
    while(i <= level * 2) {
      nums.append(numGenerator.nextInt(level)+1)
      i = i + 3
    }

    // Sort the nums array
    nums = nums.sorted

    // Calculating the product of the generated numbers
    prod = 1
    for(num <- nums)
      prod = prod * num

    // Calculating the sum of the generated numbers
    sum = 0
    for(num <- nums)
      sum = sum + num

    // Presenting the number tips
    print("~ There are " + nums.length + " numbers\n"
        + "~ The sum of the numbers is " + sum + "\n"
        + "~ The product of the numbers is " + prod + "\n\n")

    // Prompting the player for guesses
    print("* The screen displays another message:                                      *\n")

    // Collecting guesses into array
    for(i <- 1 to nums.length) {
      print("Enter your " + nth(i) + " number: ")
      try {
        guesses.append(scala.io.StdIn.readInt())
      }
      catch{
        case _: NumberFormatException =>
          thatsALetterMorty()
          guesses.append(scala.io.StdIn.readInt())
      }
    }

    // Sorting guesses
    guesses = guesses.sorted

    // Testing the arrays for equivalence
    for(j <- nums.indices) {
      if (nums(j) != guesses(j))
        proceed = false
    }

    // Emptying the nums and guesses arrays for the next level
    nums.clear()
    guesses.clear()

    // Success or fail outcomes
    if(proceed) {
      success(level)
      level = level + 1
    } else {
      failure(level)
    }

    if(!proceed || level > 5) {
      // Prompting player for a reset
      print("Would you like to play again? (y/n):  ")
      c = scala.io.StdIn.readChar()

      if (c == 'y' || c == 'Y') {
        level = 1
        proceed = true
      }
    }
  } while (proceed && level <= 5)

  def game(level: Int): Unit = {
    level match {
      case 1 =>
        // Seeding/reseeding the random number generator
        numGenerator.setSeed(System.currentTimeMillis())

        // First level message: Gazorpazorp
        print("* As you burst out of the portal, you enter a dimly lit room, LEDs and text *\n"
            + "* displays glow in the dark. You see Rick engaged in dire battle with an    *\n"
            + "* enraged Gazorpazorpian!                                                   *\n\n"
            + "Rick:  Quick! Morty! These Gazorpazorpians are real f*ckin idiots! The code\n"
            + "       is probably written down somewhere!\n"
            + "Morty: Oh crap, Rick! Okay, um, uh...\n\n"
            + "* You quickly glance around and see a conspicuous Post-It note on one of    *\n"
            + "* the computers. It reads:                                                  *\n\n")
      case 2 =>
        // Second level message: Intergalactic Federation base
        print("* As you spill out of that portal, you hear laser blasts all around you!    *\n"
            + "* You've tumbled into a full-on brawl aboard an Intergalactic Federation    *\n"
            + "* base station!                                                             *\n\n"
            + "Rick:  Damn! Morty, I'm pinned down! See if you can roll over to the\n"
            + "       console and--\n\n"
            + "* Suddenly a blast rips through the metal just next to Rick's ear, cutting  *\n"
            + "* him off.                                                                  *\n\n"
            + "Morty: Aw, geez, Rick! There's like 50 guys trying to shoot us! What if\n"
            + "       I get shot?!\n\n"
            + "Rick:  Well then let's make it easy! If you don't do it, I'll shoot you!\n\n"
            + "* You'd like to think that Rick is being overly-dramatic, but that didn't   *\n"
            + "* sound very sarcastic. During a brief lull in the firing (reloading, you   *\n"
            + "* hope) you dive for the console. Successfully there, you read:             *\n\n")
      case 3 =>
        // Third level message: the Vindicators' space station
        print("* You spill out of the portal into yet another dimly-lit room. But this one *\n"
            + "* seems familiar to you...                                                  *\n\n"
            + "Morty: Rick? Rick?\n"
            + "Rick:  Morty! Watch out!\n\n"
            + "* Just as Rick shouts, a tiny galaxy shoots past your head with a woosh.    *\n"
            + "* Suddenly you feel an invisible grip wrap around your throat. Scanning     *\n"
            + "* the room for your assailant you lock eyes with two glowing, purple orbs   *\n"
            + "* in the corner of the room. It's Supernova! Rick rolls out from his hiding *\n"
            + "* place and hits her flank with a blast of science-y light. You fell the    *\n"
            + "* throttling grip disappear.                                                *\n\n"
            + "Rick:  Quick, Morty! The console's over there.\n\n"
            + "* Rick points to a nearby console. You know the drill, you make your way    *\n"
            + "* over while avoiding the mayhem spewing from the Rick-Supernova clash.     *\n\n")
      case 4 =>
        // Fourth level message: Old Man/the Devil's shop [Sphinx]
        print("* You both fall out of the portal nearly simultaneously. This room is dusty *\n"
            + "* and full of wooden shelves covered with random minutiae...                *\n\n"
            + "Devil: Well hello Rick, we meet again...\n\n"
            + "* The Devil slowly hobbles out from a shadow, aided by a cane. His face     *\n"
            + "* twisting tortuously with every step.                                      *\n\n"
            + "Rick:  Oh damn! I thought you'd, like, super heal yourself or something\n"
            + "       after we messed you up.\n"
            + "Devil: I only get so many mortal coils per century, I'm not going to\n"
            + "       waste this one just because you put a few dents in it.\n\n"
            + "* You decide to leave the two to catch up as you search out the console you *\n"
            + "* know must be here. You begin searching the room, but everything in        *\n"
            + "* here is an antique. You don't see a computer screen anywhere. About       *\n"
            + "* to give up, you notice something odd. Hanging in the air above a nearby   *\n"
            + "* type writer, is if typed on invisible paper, you see:                     *\n\n")
      case 5 =>
        // Final level message: the Zigerion scammers' VR machine
        print("* You slowly open your eyes. Feels like you've been asleep for awhile. And  *\n"
            + "* you are in your bed, wearing your pajamas. Wait was it all a dream? you   *\n"
            + "* wonder. That would actually explain a lot, I mean adventures with Rick    *\n"
            + "* are more often crazy than not. You get out of bed and begin to get ready  *\n"
            + "* ready for school. Looking out the window, it looks like it's gonna be a   *\n"
            + "* good day. The weather looks perfect. The sun is bright. The pop-tart that *\n"
            + "* lives across the is hopping into his toaster car to head to work. Wait... *\n"
            + "* From downstairs you hear Rick shouting:                                   *\n\n"
            + "Rick:  Morty! It's the Zigerions again!\n"
            + "* You hear him running up the stairs                                        *\n"
            + "Rick:  Quick Morty, I patched your laptop into the system, quick before     *\n"
            + "       they shut you out!\n\n"
            + "* You open your laptop screen. Instead of your normal background, Jessica's *\n"
            + "* summer vacation Instagram posts, it's just a simple console, reading:     *\n\n")
      case _ =>
        print("How did you get here?\n\n")
    }
  }

  def success(level: Int): Unit = {
    // Initial formatting spaces
    println("\n")

    level match {
      case 1 =>
        print("* Success! The computer logs in just as Rick finally bests the              *\n"
            + "* battle-hardened Gazorpazorpian. Rick heads over to the console and puts  *\n"
            + "* in a few lines of code.                                                   *\n\n"
            + "Rick:  Alright Morty, on to the next junction point!\n"
            + "Morty: Right! Wait... what are we doing again?\n"
            + "Rick:  Dammit, Morty! There's no time!\n\n"
            + "* Rick shouts as he opens and immediately jumps through another portal.     *\n"
            + "* Aw geez, you think as you scramble after him.                             *\n\n")
      case 2 =>
        print("* Success! You're logged in! After Rick mops up the rest of the Federation  *\n"
            + "* soldiers, he jogs over to the console and enters a few commands.          *\n\n"
            + "Rick:  Excellent, Morty! Really, just a real *burp* bang-up job!\n"
            + "       On to the next!\n\n"
            + "* Rick opens another portal and leaps through.                              *\n\n"
            + "Morty: Wait! Rick! Dammit, what's going on?!\n\n"
            + "* You reluctantly follow Rick through the portal.                           *\n\n")
      case 3 =>
        print("* Success! The console logs into the server. You turn around in time to see *\n"
            + "* Rick being menaced by Supernova with a micro-sized Jupiter, his portal    *\n"
            + "* gun a few feet out of his reach. Just before she brings the micro-planet  *\n"
            + "* down to crush Rick, you shout                                             *\n\n"
            + "Morty: Uh - hey- uh- Supernova, what can kill a galaxy?\n\n"
            + "* As she turns in your direction, her expression changes from one of        *\n"
            + "* conquest to one of apprehension as she sees you holding the portal gun.   *\n\n"
            + "Morty: A black hole!\n\n"
            + "* You shout as you launch a portal right behind her to what you're pretty   *\n"
            + "* sure is the all black holes dimension. Or maybe it's the dimension where  *\n"
            + "* everybody takes really deep breaths. Either way, in spinning to look at   *\n"
            + "* the portal, Supernova's hand passes through the barrier, causing her to   *\n"
            + "* be sucked inside!\n\n "
            + "Rick: Nicely done, Morty! Only two more to go!\n\n"
            + "* Rick takes his portal gun, opens a new portal and hops through. As you    *\n"
            + "* follow after him you wonder how long your portal will remain open and if  *\n"
            + "* it might accidentally eat up anything else. Eh, no time for that now.     *\n\n")
      case 4 =>
        print("* Success! As you press the carriage return the final time, the invisible   *\n"
            + "* paper un-spools and floats off somewhere. You turn around and, much to    *\n"
            + "* your surprise, you see Rick and the Devil chilling out on some beanbags   *\n"
            + "* listening to some record on vinyl.                                        *\n\n"
            + "Morty: Wait, wha?\n"
            + "Rick:  It's all good Morty, we figured no need ride this carousel again.\n\n"
            + "* As Rick finishes his statement, he passes his flask to the Devil, who     *\n"
            + "* who takes a long swig from it.                                            *\n\n"
            + "Devil: Yeah, I'm not about futility, that's for the poor saps I punish.\n"
            + "Morty: Uh, okay? So we're all cool? Rick, this doesn't seem weird to you?\n"
            + "Rick:  I mean, any weirder than normal, no not really.\n\n"
            + "* He has a good point. Rick takes his flask back, gets up off the pseudo-   *\n"
            + "* chair, pulls out his portal gun, and opens a new portal.                  *\n\n"
            + "Rick:  Alright morty, one final junction point to go.\n\n"
            + "* You and Rick jump through the portal                                      *\n\n")
      case 5 =>
        print("Morty: Okay, now *this* seems really weird Rick, are we in a dream?\n"
            + "Rick:  Yes Morty, it would seem that as in all poorly-written fiction,\n"
            + "       we're just now coming out of a Dallas-esque \"everything is okay\"\n"
            + "       dream sequence What a load!\n\n"
            + "* Slowly you feel your eyes begin to open. Yup, you're in your bed, in      *\n"
            + "* your pajamas, and running 10 minutes late for school! \"Aw geez!\" you      *\n"
            + "* shout as you run down the stairs half-dressed. Slowly, Rick rises from    *\n"
            + "* his hiding place below your bed, removing a Dreamalizer from his ear.     *\n\n"
            + "Congratulations, you beat my ridiculous game! Please let me know if you\n"
            + "liked it, and feel free to fork it into your own version, just let me\n"
            + "know so I can try it :D!\n\n")
      case _ =>
        print("How did you get here?\n\n")
    }
  }

  def failure(level: Int): Unit = {
    // Initial formatting spaces
    println("\n")

    level match {
      case 1 =>
        print("* Wrong! The computer shuts down with a loud siren. You've tripped the      *\n"
            + "* alarm. Rick turns his head to chastise you for your idiocy when suddenly  *\n"
            + "* the tip of a Gazorpazorpian spear bursts forth from Rick's chest,       *\n"
            + "* wielded by another warrior entering the room.                             *\n\n")
      case 2 =>
        print("* Failure! Through a screaming siren, the console declares:                 *\n\n"
            + "Console:  Incorrect password entry, self-destruct mechanism activated,\n"
            + "          detonating in two--\n"
            + "Morty:    wait wha--?!\n"
            + "Console:  One. Destruct--\n\n"
            + "* The computer console explodes, rending a hole in the exterior hull,       *\n"
            + "* sucking you, Rick, and a good number of Federation soldiers out into the  *\n"
            + "* endless void.                                                             *\n\n")
      case 3 =>
        print("* Failure! But the console offers you another chance. It reads:             *\n\n"
            + "*** To try again, press enter ***\n\n"
            + "* As you go to press the enter key, a softball size comet whips past your   *\n"
            + "* head straight into the console screen. As the console showers you in      *\n"
            + "* sparks, you turn to Rick. As soon as you do, you feel the invisible hand  *\n"
            + "* at your throat again, this time much stronger and much more sure of your  *\n"
            + "* demise. You slowly feel the world go dark as you hear Supernova's cackle  *\n"
            + "* ringing in your ears...                                                   *\n\n")
      case 4 =>
        print("* Wrong! The typewriter disappears in a puff of smoke! You turn to Rick to  *\n"
            + "* protest, but he's not there! Nor is the Devil?! In fact, there's nothing  *\n"
            + "* at all in the room. There is no room. You're suddenly in an inky black    *\n"
            + "* void, surrounded by nothingness. The Devil has banished you to the void!  *\n\n")
      case 5 =>
        print("* Sirens begin blaring. Suddenly, you snap awake in your bed. Wait, what?   *\n"
            + "* you think to yourself. Was it all a dream? Well that doesn't matter right *\n"
            + "* now because school started 10 minutes ago! You launch yourself out of bed *\n"
            + "* looking for your one clean shirt: the yellow one. Throwing it on, you run *\n"
            + "* out the door, snagging your backpack on the way out. Hopefully today will *\n"
            + "* be a normal day, you think. The algorithm says it will.                   *\n\n")
      case _ =>
        print("How did you get here?\n\n")
    }
  }

  def nth(n: Int): String = {
    n match {
      case 1 => "first"
      case 2 => "second"
      case 3 => "third"
      case 4 => "fourth"
      case _ => "How did you get here?"
    }
  }

  def thatsALetterMorty(): Unit = {
    print("\n"
        + "* Peeking over your shoulder, right at this moment Rick *\n"
        + "* sighs exasperatedly and says:                         *\n\n"
        + "Rick:  Morty, you idiot, that's not a number!\n\n"
        + "* A number this time, please: ")
  }
}
