/*
 * This Groovy source file was generated by the Gradle 'init' task.
 */
package org.sartellcoderdojo.groovybot

import discord4j.common.util.Snowflake
import discord4j.core.*
import discord4j.core.object.entity.*
import discord4j.core.object.entity.channel.*
import discord4j.core.event.domain.message.*
import groovy.util.logging.Slf4j

import java.time.Duration
import java.time.temporal.ChronoUnit

@Slf4j
class App {

    private final GatewayDiscordClient client
    private final TextChannel groovyChannel
    private static final String TOKEN = System.getenv('GROOVY_BOT_TOKEN')
    private static final long GROOVY_CHANNEL_ID = 777571101710614588

    private static final List<String> INSULTS = [
            "You couldn't code your way out of a cardboard bag",
            "My grandma writes better code than you, and she's not with us anymore",
            "Your code looks like something that would be typed by a cat walking across the keyboard",
            "wanna sprite cranberry",
    ]

    App(String token = TOKEN) {
        log.info("Token = $token")
        client = DiscordClient.create(token).login().block()
        groovyChannel = client.getChannelById(Snowflake.of(GROOVY_CHANNEL_ID)).block() as TextChannel

        log.info("Connected")
        send("Hello")
        client.on(MessageCreateEvent).subscribe({ handleMessage(it) })

        addShutdownHook {
            log.warn('Shutting down')
            send('Goodbye')
        }
    }

    static void main(String[] args) {
        new App()
        while (true) {

        }
    }

    def handleMessage(MessageCreateEvent event) {
        Message message = event.getMessage()
        def authorName = message.getAuthor().get().getUsername()
        if (authorName != 'Groovy Bot') {
          log.info("Recieved message: ${event.message.content}")
          MessageChannel channel = event.message.channel.block()
          log.info("from channel ${channel.name}: $channel")
          switch (message.content) {
              case "!ping":
                  replyTo(message, "Pong!")
                  break
              case "!insult":
                  replyTo(message, INSULTS[Math.random() * INSULTS.size()])
                  break
          }
          if (message.content.toLowerCase() ==~ /.*v[aeiou]l[aeiou]nt[aeiou]n[aeiou]?s.*/) {
            replyTo(message, '***Happy*** Valentine\'s Day! ❤️❤️❤️❤️❤️')
            replyTo(message, 'https://giphy.com/embed/26DNdwaQd703CekaQ')
          }
        }
    }

    def send(String message) {
        groovyChannel.createMessage(message).block(Duration.of(20, ChronoUnit.SECONDS))
    }

    def replyTo(Message message, String reply) {
        message.getChannel().block().createMessage(reply).block()
    }
}
