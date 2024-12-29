# Minecraft-plugin- Cooldown
This plugin allows the user to add a cooldown for a specific user and material.

## Usage
```sh
/cooldown <User Name> <Material> <Time (seconds)>
/cooldown-list
/cooldown-remove <User Name>
```

## Internal workflows
Users and materials are stored in HashMap, similar to this JSON:
```json
{
    "user_id": [{
        "date": "date",
        "material": "material"
    }]
}
```

This plugin also provides an integration with the Placeholder API. The only available placeholder is `%cooldownPlugin_cooldown_USER_MATERIAL`, that returns `true` or `false` if the user has a cooldown in that specified Material.
