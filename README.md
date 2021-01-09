# groovy-bot
Discord Bot Implemented In Groovy

# getting started

```sh
brew install openjdk
echo 'export PATH="/usr/local/opt/openjdk/bin:$PATH"' >> ~/.zshrc
source ~/.zshrc
brew install gradle
```

With openjdk and gradle installed,
1. Download the code into a folder on your filesystem
1. Open a command terminal
1. Navigate to the root folder of the
1. Set the GROOVY_BOT_TOKEN environment variable
  - On MacOS or Linux run `export GROOVY_BOT_TOKEN={token goes here}`
  - On Windows run `set GROOVY_BOT_TOKEN={token goes here}`
1. Run `./gradle run`


# testing

```sh
./gradlew clean test
```
